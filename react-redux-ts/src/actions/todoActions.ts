export const ADD_TODO = 'ADD_TODO';
export const TOGGLE_TODO = 'TOGGLE_TODO';

interface AddTodoAction {
    type: typeof ADD_TODO,
    text: string
}

interface ToggleTodoAction {
    type: typeof TOGGLE_TODO,
    index: number
}

export function addTodo(text: string): AddTodoAction {
    return {type: ADD_TODO, text}
}

export function toggleTodo(index: number): ToggleTodoAction {
    return {type: TOGGLE_TODO, index}
}

export interface Todo {
    text: string,
    completed: boolean
}

export type TodoActionTypes = AddTodoAction | ToggleTodoAction

