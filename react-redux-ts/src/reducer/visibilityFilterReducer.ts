import {SET_VISIBILITY_FILTER, VisibilityFilters} from "../actions/visibilityActions";

function visibilityFilterReducer(state = VisibilityFilters.SHOW_ALL, action: any) {
    switch (action.type) {
        case SET_VISIBILITY_FILTER:
            return action.filter;
        default:
            return state;
    }
}

export default visibilityFilterReducer;
