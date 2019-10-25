import React from 'react'

export interface TodoProps {
    onClick: () => void
    onDelete: () => void
    completed: boolean
    text: string
}

const TodoComponent: React.FC<TodoProps> = ({onClick, onDelete, completed, text}) => (
    <li>
        <span onClick={onClick}
              style={{
                  textDecoration: completed ? 'line-through' : 'none'
              }}>{text}</span>
        <i aria-hidden="true" className="users delete icon" onClick={onDelete}></i>
    </li>
)

export default TodoComponent
