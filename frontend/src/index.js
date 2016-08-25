import 'babel-polyfill'
import React from 'react'
import { render } from 'react-dom'
import App from './containers/App'
import Home from './components/Home'
import Books from './components/Books'
import Login from './components/Login'

import { Router, Route, IndexRoute, browserHistory } from 'react-router'

render(
    <Router history={browserHistory}>
        <Route path='/' component={App}>
            <IndexRoute component={Home} />
            <Route path='login' component={Login} />
            <Route path='books' component={Books} />
        </Route>
    </Router>,
    document.getElementById('root')
);