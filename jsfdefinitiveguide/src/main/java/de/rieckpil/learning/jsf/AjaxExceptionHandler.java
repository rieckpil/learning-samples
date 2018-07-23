package de.rieckpil.learning.jsf;

import javax.el.ELException;
import javax.faces.FacesException;
import javax.faces.application.Application;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.*;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.view.ViewDeclarationLanguage;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class AjaxExceptionHandler extends ExceptionHandlerWrapper {

    public AjaxExceptionHandler(ExceptionHandler wrapped) {
        super(wrapped);
    }

    @Override
    public void handle() {
        handleAjaxException(FacesContext.getCurrentInstance());
        getWrapped().handle();
    }

    protected void handleAjaxException(FacesContext context) {
        Iterator<ExceptionQueuedEvent> unhandledExceptionQueuedEvents =
                getUnhandledExceptionQueuedEvents().iterator();

        if (context == null
                || context.getExternalContext().isResponseCommitted()
                || !context.getPartialViewContext().isAjaxRequest()
                || !unhandledExceptionQueuedEvents.hasNext()) {
            return;
        }
        Throwable exception = unhandledExceptionQueuedEvents.next().getContext().getException();
        while (exception.getCause() != null
                && (exception instanceof FacesException
                || exception instanceof ELException)) {
            exception = exception.getCause();
        }

        ExternalContext external = context.getExternalContext();
        String uri = external.getRequestContextPath() + external.getRequestServletPath();
        Map<String, Object> requestScope = external.getRequestMap();
        requestScope.put(RequestDispatcher.ERROR_REQUEST_URI, uri);
        requestScope.put(RequestDispatcher.ERROR_EXCEPTION, exception);
        String viewId = "/WEB-INF/errorpages/500.xhtml";
        Application application = context.getApplication();
        ViewHandler viewHandler = application.getViewHandler();
        UIViewRoot viewRoot = viewHandler.createView(context, viewId);
        context.setViewRoot(viewRoot);
        try {
            external.responseReset();
            ViewDeclarationLanguage viewDeclarationLanguage =
                    viewHandler.getViewDeclarationLanguage(context, viewId);
            viewDeclarationLanguage.buildView(context, viewRoot);
            context.getPartialViewContext().setRenderAll(true);
            viewDeclarationLanguage.renderView(context, viewRoot);
            context.responseComplete();
        } catch (IOException e) {
            throw new FacesException(e);
        } finally {
            requestScope.remove(RequestDispatcher.ERROR_EXCEPTION);
        }
        unhandledExceptionQueuedEvents.remove();
        while (unhandledExceptionQueuedEvents.hasNext()) {
            unhandledExceptionQueuedEvents.next();
            unhandledExceptionQueuedEvents.remove();
        }
    }

    public static class Factory extends ExceptionHandlerFactory {

        public Factory(ExceptionHandlerFactory wrapped) {
            super(wrapped);
        }

        @Override
        public ExceptionHandler getExceptionHandler() {
            return new AjaxExceptionHandler(getWrapped().getExceptionHandler());
        }
    }
}