import React from 'react';
import ReactDOM from 'react-dom';
import {Provider} from 'react-redux';

import {reducers, TodoAppState} from './reducer/reducers';
import App from './App';
import * as serviceWorker from './serviceWorker';
import {Action, createStore, Store} from "redux";
import {VisibilityFilters} from "./actions/visibilityActions";

import 'semantic-ui-css/semantic.min.css'

const initialState: TodoAppState = {
    todos: [],
    visbilityFilter: VisibilityFilters.SHOW_ALL
};

const store: Store<TodoAppState> = createStore<TodoAppState, Action, null, null>(reducers, initialState);

console.log(store.getState())

store.subscribe(() => console.log(store.getState()))


ReactDOM.render(
    <Provider store={store}>
        <App/>
    </Provider>,
    document.getElementById('root'));

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
