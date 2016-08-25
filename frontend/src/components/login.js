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
            <div className='row'>
                <div className='col-md-5'>
                    <form role='form'>
                        <div className='form-group'>
                            <label htmlFor='exampleInputEmail1'>Username</label>
                            <input
                                className='form-control'
                                type='text'
                                placeholder='Username'
                                onChange={this.__usernameChange.bind(this)}
                                value={this.state && this.state.username}/>
                        </div>
                        <div className='form-group'>
                            <label htmlFor='exampleInputPassword1'>Password</label>
                            <input
                                className='form-control'
                                type='password'
                                placeholder='Password'
                                onChange={this.__passwordChange.bind(this)}
                                value={this.state && this.state.password || ''}/>
                        </div>
                        <button className='btn btn-default' type='submit' onClick={this.__submit.bind(this)}>
                            log in
                        </button>
                    </form>
                </div>
            </div>
        )
    }
}