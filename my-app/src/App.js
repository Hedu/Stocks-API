import React from 'react';
import StockList from './StockList';
import AddStock from './AddStock';
import StockDetail from './StockDetail';

import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link
} from "react-router-dom";

function App() {
  return (
    <Router>
          <div>
            <nav>
              <ul>
                <li>
                  <Link to="/">List of Stocks</Link>
                </li>
                <li>
                  <Link to="/add-stock">Add Stock</Link>
                </li>
              </ul>
            </nav>

            {/* A <Switch> looks through its children <Route>s and
                renders the first one that matches the current URL. */}
            <Switch>
              <Route path="/stock">
                <StockDetail />
              </Route>
              <Route path="/add-stock">
                <AddStock />
              </Route>
              <Route path="/">
                <StockList />
              </Route>
            </Switch>
          </div>
        </Router>
  );
}

export default App;
