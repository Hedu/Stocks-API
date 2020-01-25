import React from 'react';

class StockDetail extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      id: window.location.pathname.split("/")[2],
      item: {},
       isSubmitting: false,
       isError: false
    };
  }

  componentDidMount() {
      fetch("http://localhost:8080/api/stocks/" + this.state.id)
        .then(res => res.json())
        .then(
          (result) => {
            this.setState({
              isLoaded: true,
              item: result
            });
          },

          (error) => {
            this.setState({
              isLoaded: true,
              error
            });
          }
        )
    }

  handleInputChange = e =>
      this.setState({
        item: { ...this.state.item, [e.target.name]: e.target.value }
  });

  submitForm = e => {
      e.preventDefault();

      fetch("http://localhost:8080/api/stocks/" + this.state.item.id, {
          method: 'PUT',
          body: '{"currentPrice": "' + this.state.item.currentPrice + '"}',
          headers: {
              'Content-Type': 'application/json'
          }
      })
      .then(res => {
        this.setState({ isSubmitting: false });
         if (res.status == 200) {
                document.location.href="/";
        }
        else {
            console.log(res.json());
            alert("Problem updating the stock:" + res.json().message);
        }
      });
  }

  render() {
    const { error, isLoaded, item } = this.state;

    if (error) {
      return <div>Error: {error.message}</div>;
    } else if (!isLoaded) {
      return <div>Loading...</div>;
    } else {
      return (
        <React.Fragment>
          <h2>Stock Info</h2>
          <div>
            <p>ID: {item.id}</p>
            <p>Name: {item.name}</p>
            <p>Last Updated: {item.lastUpdate}</p>
            <form onSubmit={this.submitForm}>
                <div className="input-group">
                    <label htmlFor="currentPrice">Price</label>
                    <input
                        name="currentPrice"
                        id="currentPrice"
                        value={item.currentPrice}
                        onChange={this.handleInputChange}
                        title="Price"
                        required
                    />
                </div>
                <button type="submit">Update Price</button>
            </form>
          </div>
        </React.Fragment>
      );
    }
  }
}

export default StockDetail;