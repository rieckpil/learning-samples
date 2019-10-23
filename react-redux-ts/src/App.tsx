import React, {useState} from 'react';
import {TodoAppState} from "./reducer/reducers";
import {connect} from "react-redux";
import {addTodo, Todo, toggleTodo} from "./actions/todoActions";
import {AnyAction, Dispatch} from "redux";
import TodoComponent from "./TodoComponent";

type AllProps = {
    todos: Todo[]
    addNewTodo: (text: string) => void
    toggleExistingTodo: (index: number) => void
}

const App: React.FC<AllProps> = ({todos, addNewTodo, toggleExistingTodo}) => {

    const [todoText, setTodoText] = useState('Learn React');

    return (
        <div>
            <p>Todo List</p>
            <div>
                <input type='text'
                       value={todoText}
                       onChange={e => setTodoText(e.target.value)}/>
                <br/>
                <button onClick={() => addNewTodo(todoText)}>Add new</button>
                {
                    todos.map((todo, index) =>
                        <TodoComponent
                            key={index}
                            onClick={() => toggleExistingTodo(index)}
                            completed={todo.completed}
                            text={todo.text}
                        />)
                }
            </div>
        </div>
    );
};

const mapStateToProps = (state: TodoAppState) => state;
const mapDispatchToProps = (dispatch: Dispatch<AnyAction>) => {
    return {
        addNewTodo: (text: string) => dispatch(addTodo(text)),
        toggleExistingTodo: (index: number) => dispatch(toggleTodo(index))
    }
};

export default connect(mapStateToProps, mapDispatchToProps)(App);
