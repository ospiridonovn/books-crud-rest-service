var book = {
    author: 'Саша Печкин',
    title: 'В четчерг, четвертого числа...',
    releaseYear: 10
};

var books = [
    {
        author: 'Саша Печкин',
        title: 'В четчерг, четвертого числа...',
        releaseYear: 10
    },
    {
        author: 'Саша Печкин',
        title: 'В четчерг, четвертого числа...',
        releaseYear: 10
    },    {
        author: 'Саша Печкин',
        title: 'В четчерг, четвертого числа...',
        releaseYear: 10
    }
];


var Book = React.createClass({

    render: function() {
        var author = this.props.data.author,
            title = this.props.data.title,
            releaseYear = this.props.data.releaseYear;

        return (
            <div>
                <div className="row">
                    <div className="col-md-3">
                        <p>Author:</p>
                    </div>
                    <div className="col-md-3">
                        <p>{author}:</p>
                    </div>
                </div>
                <div className="row">
                    <div className="col-md-3">
                        <p>Title:</p>
                    </div>
                    <div className="col-md-3">
                        <p>{title}:</p>
                    </div>
                </div>
                <div className="row">
                    <div className="col-md-3">
                        <p>Release Year:</p>
                    </div>
                    <div className="col-md-3">
                        <p>{releaseYear}:</p>
                    </div>
                </div>
            </div>
        )
    }
});

var BookInTable = React.createClass({
   render: function () {
       var author = this.props.data.author,
           title = this.props.data.title,
           releaseYear = this.props.data.releaseYear;

       return (
           <tr>
               <td>
                   <p>{author}</p>
               </td>
               <td>
                   <p>{title}</p>
               </td>
               <td>
                   <p>{releaseYear}</p>
               </td>
           </tr>

       )
   }
});

var Books = React.createClass({

    propTypes: {
        data: React.PropTypes.array.isRequired
    },

    getBooks: function (response) {
        this.setState({
            searchResults: response.results
        })
    },

    search: function (URL) {
        $.ajax({
            type: "GET",
            url: URL,
            success: function (response) {
                this.setState({searchResults: response.results})
            }.bind(this)
        })
    },

    componentDidMount() {
        this.search('http://localhost:9081/WebModule/book/');
    },

    render: function() {
        var data = this.props.data;
        var searchResults = this.props.searchResults;
        var booksTemplate;

        if (data.length > 0) {
            booksTemplate = data.map(function(item, index) {
                return (
                    <BookInTable key={index} data={item} />

                )
            })
        } else {
            booksTemplate = <p>К сожалению новостей нет</p>
        }

        return (
            <div>
                <table className="table table-bordered table-hover table-condensed">
                    <thead>
                        <tr>
                            <th>Author</th>
                            <th>Title</th>
                            <th>Release Year</th>
                        </tr>
                    </thead>
                    <tbody>
                        {booksTemplate}
                    </tbody>
                </table>
            </div>
        )
    }
});



var Login = React.createClass({
    getInitialState: function () {
        return {}
    },

    submit: function (e) {
        var self;

        e.preventDefault();
        self = this;

        var data = {
            username: this.state.username,
            password: this.state.password
        };

        $.ajax({
            type: 'GET',
            url: 'http://localhost:9081/WebModule/login/user-password',
            data: data
        })

        .done(function(data) {
            self.clearForm()
        })

        .fail(function(jqXhr) {
            console.log('failed to register');
        });
    },

    usernameChange: function(event) {
        this.setState({username: event.target.username});
    },

    passwordChange: function (event) {
        this.setState({password: event.target.password});
    },

    render: function() {
        return (
            <form className="form-signin" onSubmit={this.submit}>
                <h2 className="form-signin-heading">Please sign in</h2>
                <input
                    className="form-control"
                    type="text"
                    placeholder="Username"
                    onChange={this.usernameChange}
                    value={this.state.username}/>
                <input
                    className="form-control"
                    type="password"
                    placeholder="Password"
                    onChange={this.passwordChange}
                    value={this.state.password}/>
                <button className="btn btn-lg btn-primary btn-block" type="submit">
                    Log in
                </button>
            </form>
        )
    }

});

ReactDOM.render(
    <Books data={books}/>,
    // <Login/>,
    document.getElementById('root')
);