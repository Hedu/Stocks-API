import React from 'react';
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link
} from "react-router-dom";

class StockList extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      error: null,
      isLoaded: false,
      items: []
    };
  }

  componentDidMount() {
    fetch("http://localhost:8080/api/stocks")
      .then(res => res.json())
      .then(
        (result) => {
          this.setState({
            isLoaded: true,
            items: result
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

  render() {
    const { error, isLoaded, items } = this.state;
    if (error) {
      return <div>Error: {error.message}</div>;
    } else if (!isLoaded) {
      return <div>Loading...</div>;
    } else {

      return (
        <React.Fragment>
          <h2>List of Stocks</h2>
          <div>
          {items.length == 0?(
            <p>The list is empty, you could add some stocks</p>
          ):(
            <ul>
               {items.map(item => (
                 <li key={item.name}>
                 <Link to={"/stock/" + item.id}>ID:{item.id} Name:{item.name} Price:{item.currentPrice} Last Updated:{item.lastUpdate}</Link>
                 </li>
               ))}
             </ul>
          )}
          </div>
        </React.Fragment>
      );
    }
  }
}

export default StockList;