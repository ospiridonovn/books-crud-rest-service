import React, { Component } from 'react'
import $ from 'jquery'
import cookie from 'react-cookie';

export default class Books extends Component {
    constructor(props) {
        super(props);
        this.state = {
            id: '',
            title: '',
            author: '',
            releaseYear: '',
            isNewBook: false

        };
    }

    componentWillMount() {
        this.__searchAll('http://localhost:9081/WebModule/book/');
    }

    __searchAll(URL) {
        $.ajax({
            type: 'GET',
            url: URL,
            dataType: 'json',
            success: function (response) {
                this.setState({searchResults: response})
            }.bind(this)
        })
    }


    __submit(e) {
        console.log(e);
        console.log('fasdef');
        var data;

        console.log(this.state.isNewBook);


        console.log(JSON.stringify(data));
        if (!this.state.isNewBook) {
            data = {
                id: this.state.id,
                title: this.state.title,
                author: this.state.author,
                releaseYear: this.state.releaseYear
            };

            $.ajax({
                type: 'PUT',
                url: 'http://localhost:9081/WebModule/book/',
                headers: {'token': cookie.load('token')},
                contentType: 'application/json',
                data: JSON.stringify(data),
                success: function (response) {
                    console.log(response);
                }.bind(this)
            })

                .done(function() {
                    console.log('Send successful');
                })

                .fail(function() {
                    console.log('failed to send');
                });
        } else {
            data = {
                title: this.state.title,
                author: this.state.author,
                releaseYear: this.state.releaseYear
            };
            $.ajax({
                type: 'POST',
                url: 'http://localhost:9081/WebModule/book/',
                headers: {'token': cookie.load('token')},
                contentType: 'application/json',
                data: JSON.stringify(data),
                success: function (response) {
                    console.log(response);
                }.bind(this)
            })

                .done(function() {
                    console.log('Send successful');
                })

                .fail(function() {
                    console.log('failed to send');
                });
        }
    }

    __idChange(event) {
        this.setState({id: event.target.value});
    }

    __titleChange(event) {
        this.setState({title: event.target.value});
    }

    __authorChange(event) {
        this.setState({author: event.target.value});
    }

    __releaseYearChange(event) {
        this.setState({releaseYear: event.target.value});
    }

    __isNewBookChange(event) {
        this.setState({isNewBook: event.target.checked});
    }

    render() {
        const searchResults = (this.state && this.state.searchResults) ? this.state.searchResults : [];
        let booksTemplate;



        if (searchResults.length > 0) {
            booksTemplate = searchResults.map(function(item, index) {
                return (
                    <BookInTable key={index} data={item}/>
                )
            })
        } else {
            booksTemplate = <tr><td colSpan='3'>No books</td></tr>
        }

        return (
            <div className='row'>
                <div className='row'>
                    <table className='table table-bordered table-hover table-condensed'>
                        <thead>
                        <tr>
                            <th>Id</th>
                            <th>Author</th>
                            <th>Title</th>
                            <th>Release Year</th>
                            <th>Delete</th>
                        </tr>
                        </thead>
                        <tbody>
                            {booksTemplate}
                        </tbody>
                    </table>
                </div>




                <div id='edit-block' className='row'>
                    <form className='form-inline'>
                        <input
                            id='id-input'
                            className='form-control'
                            type='text'
                            placeholder='Id'
                            onChange={this.__idChange.bind(this)}
                            value={this.state.id}/>
                        <input
                            id='title-input'
                            className='form-control'
                            type='text'
                            placeholder='Title'
                            onChange={this.__titleChange.bind(this)}
                            value={this.state.title}/>
                        <input
                            id='author-input'
                            className='form-control'
                            type='text'
                            placeholder='Author'
                            onChange={this.__authorChange.bind(this)}
                            value={this.state.author}/>
                        <input
                            id='release-year-input'
                            className='form-control'
                            type='text'
                            placeholder='Release Year'
                            onChange={this.__releaseYearChange.bind(this)}
                            value={this.state.releaseYear}/>
                        <label>
                            <input
                                id='is-new-book'
                                className='checkbox'
                                type='checkbox'
                                onChange={this.__isNewBookChange.bind(this)}
                                value={this.state.isNewBook}/>
                            Create new book
                        </label>
                        <button className='btn btn-default' type='button' onClick={::this.__submit}>
                            Save
                        </button>
                    </form>
                </div>
            </div>
        )
    }
}

var BookInTable = React.createClass({

    __deleteBook() {

        $.ajax({
            type: 'DELETE',
            url: 'http://localhost:9081/WebModule/book/' + this.props.data.id,
            headers: {'token': cookie.load('token')},
            success: function (response) {
                console.log(response);
            }.bind(this)
        });

    },

    render: function () {
        var id = this.props.data.id,
            author = this.props.data.author,
            title = this.props.data.title,
            releaseYear = this.props.data.releaseYear;

        return (
            <tr>
                <td>
                    <p>{id}</p>
                </td>
                <td>
                    <p>{author}</p>
                </td>
                <td>
                    <p>{title}</p>
                </td>
                <td>
                    <p>{releaseYear}</p>
                </td>
                <td>
                    <button className='btn' type='button' onClick={this.__deleteBook}>
                        Delete
                    </button>
                </td>
            </tr>

        )
    }
});