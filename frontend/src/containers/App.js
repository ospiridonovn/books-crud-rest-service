import React, { Component } from 'react'
import { Link } from 'react-router'

export default class App extends Component {
  render() {
    return (
        <div className='container'>
          <h1>Application</h1>
          <ul>
            <li><Link to='/login'>Login</Link></li>
            <li><Link to='/books'>Books</Link></li>
          </ul>
          {/* добавили вывод потомков */}
          {this.props.children}
        </div>
    )
  }
}