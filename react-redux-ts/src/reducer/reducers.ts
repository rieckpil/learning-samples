import {combineReducers, Reducer} from 'redux'
import todoReducer from "./todoReducer";
import visibilityFilterReducer from "./visibilityFilterReducer";
import {Todo} from "../actions/todoActions";
import {VisibilityFilters} from "../actions/visibilityActions";

export interface TodoAppState {
    todos: Todo[]
    visbilityFilter: VisibilityFilters
}

export const reducers: Reducer<TodoAppState> = combineReducers<TodoAppState>({
    todos: todoReducer,
    visbilityFilter: visibilityFilterReducer
});

