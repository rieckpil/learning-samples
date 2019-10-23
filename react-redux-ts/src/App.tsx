import React, { useState } from 'react';
import { TodoAppState } from "./reducer/reducers";
import { connect } from "react-redux";
import { addTodo, Todo, toggleTodo } from "./actions/todoActions";
import { AnyAction, Dispatch } from "redux";
import TodoComponent from "./TodoComponent";
import { Container, Header, Input, Button } from 'semantic-ui-react';

type AppComponentProps = {
    todos: Todo[]
    addNewTodo: (text: string) => void
    toggleExistingTodo: (index: number) => void
}

const App: React.FC<AppComponentProps> = ({ todos, addNewTodo, toggleExistingTodo }) => {

    const [todoText, setTodoText] = useState('Learn React');

    return (
        <div>
            <Container>
                <Header as='h2' style={{ marginTop: '5px' }} >Todo List with React, Redux and TypeScript</Header>
                <Input type='text'
                    value={todoText}
                    onChange={e => setTodoText(e.target.value)}
                    style={{ marginRight: '5px' }} />
                <Button onClick={() => addNewTodo(todoText)}>Add new</Button>
                <Header as='h2'>My Todo List</Header>
                {
                    todos.map((todo, index) =>
                        <TodoComponent
                            key={index}
                            onClick={() => toggleExistingTodo(index)}
                            completed={todo.completed}
                            text={todo.text}
                        />)
                }
            </Container>
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
