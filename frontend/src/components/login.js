import React, { Component } from 'react'
import $ from 'jquery'
import cookie from 'react-cookie';

export default class Login extends Component {
    constructor(props) {
        super(props);
        this.state = { username: '', password: '' };
    }

    __submit(e) {

        e.preventDefault();

        var data = {
            username: this.state.username,
            password: this.state.password
        };

        $.ajax({
            type: 'POST',
            url: 'http://localhost:9081/WebModule/login/',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (response) {
                console.log(response);
                cookie.save('token',response);
            }.bind(this)
        })

            .done(function() {
                console.log('Login successful');
            })

            .fail(function() {
                console.log('failed to register');
            });
    }

    __usernameChange(event) {
        this.setState({username: event.target.value});
    }

    __passwordChange(event) {
        this.setState({password: event.target.value});
    }

    render() {
        return (
            <form className='form-signin' >
                <h2 className='form-signin-heading'>Please sign in</h2>
                <input
                    className='form-control'
                    type='text'
                    placeholder='Username'
                    onChange={this.__usernameChange.bind(this)}
                    value={this.state && this.state.username}/>
                <input
                    className='form-control'
                    type='password'
                    placeholder='Password'
                    onChange={this.__passwordChange.bind(this)}
                    value={this.state && this.state.password || ''}/>
                <button className='btn btn-lg btn-primary btn-block' type='submit' onClick={this.__submit.bind(this)}>
                    log in
                </button>
            </form>
        )
    }
}

// var Login = React.createClass({
//     getInitialState: function () {
//         return {
//             username: '',
//             password: ''
//         }
//     },
//
//     __submit: function (e) {
//         var self;
//
//         e.preventDefault();
//         self = this;
//
//         var data = {
//             username: this.state.username,
//             password: this.state.password
//         };
//
//         $.ajax({
//             type: 'POST',
//             url: 'http://localhost:9081/WebModule/login/',
//             contentType: 'application/json',
//             data: JSON.stringify(data),
//             success: function (response) {
//                 console.log(response);
//             }.bind(this)
//         })
//
//             .done(function(data) {
//                 console.log('Login successful');
//             })
//
//             .fail(function(jqXhr) {
//                 console.log('failed to register');
//             });
//     },
//
//     __usernameChange: function(event) {
//         this.setState({username: event.target.value});
//     },
//
//     __passwordChange: function (event) {
//         this.setState({password: event.target.value});
//     },
//
//     render: function() {
//         return (
//             <form className='form-signin' >
//                 <h2 className='form-signin-heading'>Please sign in</h2>
//                 <input
//                     className='form-control'
//                     type='text'
//                     placeholder='Username'
//                     onChange={this.__usernameChange}
//                     value={this.state.username}/>
//                 <input
//                     className='form-control'
//                     type='password'
//                     placeholder='Password'
//                     onChange={this.__passwordChange}
//                     value={this.state.password}/>
//                 <button className='btn btn-lg btn-primary btn-block' type='submit' onClick={this.__submit}>
//                     log in
//                 </button>
//             </form>
//         )
//     }
//
// });
//
// ReactDOM.render(
//     <Login/>,
//     document.getElementById('root')
// );