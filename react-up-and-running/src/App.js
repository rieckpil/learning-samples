import React, {Component} from 'react';
import logo from './logo.svg';
import './App.css';

class App extends Component {

    componentWillUpdate = () => {
        console.log('Component will update');
    }

    componentWillMount = () => {
        console.log('Component will mount');
    }

    render() {
        return (
            <div className="App">
                <header className="App-header">
                    <img src={logo} className="App-logo" alt="logo"/>
                    <h1 className="App-title">Welcome to React with rieckpil!</h1>
                </header>
                <p className="App-intro">
                    <textarea></textarea>
                    To get started, edit <code>src/App.js</code> and save to reload.
                </p>
            </div>
        );
    }
}

export default App;
