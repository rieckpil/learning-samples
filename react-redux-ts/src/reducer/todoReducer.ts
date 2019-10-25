import {ADD_TODO, DELETE_TODO, Todo, TodoActionTypes, TOGGLE_TODO} from "../actions/todoActions";

function todoReducer(state = Array<Todo>(), action: TodoActionTypes) {
    switch (action.type) {
        case ADD_TODO:
            return [
                ...state,
                {
                    text: action.text,
                    completed: false
                }
            ]
        case DELETE_TODO:
            return [
                ...state.slice(0, action.index),
                ...state.slice(action.index + 1)
            ]
        case TOGGLE_TODO:
            return state.map((todo, index) => {
                if (index === action.index) {
                    return Object.assign({}, todo, {
                        completed: !todo.completed
                    })
                }
                return todo
            })
        default:
            return state
    }
}

export default todoReducer
