export const ADD_TODO = 'ADD_TODO';
export const TOGGLE_TODO = 'TOGGLE_TODO';
export const DELETE_TODO = 'DELETE_TODO';

interface AddTodoAction {
    type: typeof ADD_TODO,
    text: string
}

interface ToggleTodoAction {
    type: typeof TOGGLE_TODO,
    index: number
}

interface DeleteTodoAction {
    type: typeof DELETE_TODO,
    index: number
}

export function addTodo(text: string): AddTodoAction {
    return {type: ADD_TODO, text}
}

export function toggleTodo(index: number): ToggleTodoAction {
    return {type: TOGGLE_TODO, index}
}

export function deleteTodo(index: number): DeleteTodoAction {
    return {type: DELETE_TODO, index}
}

export interface Todo {
    text: string,
    completed: boolean
}

export type TodoActionTypes = AddTodoAction | ToggleTodoAction | DeleteTodoAction

