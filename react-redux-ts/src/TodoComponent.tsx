import React from 'react'

export interface TodoProps {
    onClick: () => void
    completed: boolean
    text: string
}

const TodoComponent: React.FC<TodoProps> = ({onClick, completed, text}) => (
    <li onClick={onClick}
        style={{
            textDecoration: completed ? 'line-through' : 'none'
        }}>
        {text}
    </li>
)

export default TodoComponent
