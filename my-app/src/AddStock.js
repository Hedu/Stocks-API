import React from 'react';

class AddStock extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      values: {
        name: "",
        currentPrice: ""
      },
       isSubmitting: false,
       isError: false
    };
  }

  handleInputChange = e =>
      this.setState({
        values: { ...this.state.values, [e.target.name]: e.target.value }
  });

  submitForm = e => {
      e.preventDefault();

      fetch("http://localhost:8080/api/stocks", {
          method: 'POST',
          body: JSON.stringify(this.state.values),
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
            alert("Problem adding the stock:" + res.json().message);
        }
      })
  }

  render() {
    const { error, isLoaded, items } = this.state;

      return (
        <React.Fragment>
          <h2>Add Stocks</h2>
          <div>
            <form onSubmit={this.submitForm}>
                <div className="input-group">
                    <label htmlFor="name">Name</label>
                    <input
                        name="name"
                        id="name"
                        value={this.state.values.name}
                        onChange={this.handleInputChange}
                        title="Name"
                        required
                    />
                </div>
                <div className="input-group">
                    <label htmlFor="currentPrice">Price</label>
                    <input
                        name="currentPrice"
                        id="currentPrice"
                        value={this.state.values.currentPrice}
                        onChange={this.handleInputChange}
                        title="Price"
                        required
                    />
                </div>
                <button type="submit">Add Stock</button>
            </form>
          </div>
        </React.Fragment>
      );
  }
}

export default AddStock;