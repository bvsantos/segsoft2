import React from 'react';
import {BrowserRouter,Route,Switch} from 'react-router-dom';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import './App.css';

import Login from './components/Login';
import Register from './components/Register';
import Delete from './components/Delete';
import ChangePassword from './components/ChangePassword';
import OurNavBar from './components/OurNavBar';

function App() {


  return (
    <BrowserRouter>
            <Switch>
              <Route exact path="/">
                <OurNavBar/>
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
