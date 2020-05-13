import React from 'react';
import {BrowserRouter,Route,Switch} from 'react-router-dom';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import logo from './logo.svg';
import './App.css';

import Login from './components/Login';
import Register from './components/Register';
import Delete from './components/Delete';
import ChangePassword from './components/ChangePassword'

function App() {


  return (
    <BrowserRouter>
            <Switch>
              <Route exact path="/">
              <div className="App">
                <header className="App-header">
                  <img src={logo} className="App-logo" alt="logo" />
                  <p>
                    Edit <code>src/App.js</code> and save to reload.
                  </p>
                  <a
                    className="App-link"
                    href="https://reactjs.org"
                    target="_blank"
                    rel="noopener noreferrer"
                  >
                    Learn React
                  </a>
                </header>
              </div>
              </Route>
              <Route exact path='/login'>
                <Login/>
              </Route>
              <Route exact path='/register'>
                <Register/>
              </Route>
              <Route exact path='/delete'>
                <Delete/>
              </Route>
              <Route exath path='/changepassword'>
                <ChangePassword/>
              </Route>
          </Switch>
      </BrowserRouter>
    
  );
}

export default App;
