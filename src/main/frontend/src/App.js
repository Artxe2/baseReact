import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';

class App extends Component {
  state = {
    isLoading: true,
    groups: []
  };

  async componentDidMount() {
    const response = await fetch('/base/current_time');
    const body = await response.json();
    this.setState({ res: body, isLoading: false });
  }

  render() {
    const {res, isLoading} = this.state;

    if (isLoading) {
      return (
        <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <div className="App-intro">
            <h2>Response</h2>
            <p>Loading...</p>
          </div>
        </header>
      </div>
      )
    }
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <div className="App-intro">
            <h2>Response</h2>
            {res}
          </div>
        </header>
      </div>
    );
  }
}

export default App;