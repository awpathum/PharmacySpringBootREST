import React, { Component } from "react";
import { Route, Switch, Redirect } from "react-router-dom";
import "./App.css";
import Home from "./components/home";
import NavBar from "./components/navBar";
import Suppliers from "./components/suppliers/supplier";
import Drugs from "./components/drugs/drugs";
import Bills from "./components/bills/bills";
import BillForm from './components/bills/billForm'
import NotFound from "./components/notfound";
import SupplierForm from "./components/suppliers/supplierForm";
import Stocks from "./components/stocks/stocks";
import StockForm from "./components/stocks/stockForm";
import DrugForm from './components/drugs/drugForm';
class App extends Component {
  render() {
    return (
      <React.Fragment>
        <NavBar></NavBar>
        <main className="container">
          <Switch>
            <Route path="/home" component={Home}></Route>
            <Route path="/suppliers/:id" component={SupplierForm}></Route>
            <Route path="/suppliers/new" exact component={SupplierForm}></Route>
            <Route path="/suppliers" component={Suppliers}></Route>
            <Route path="/stocks/:id" component={StockForm}></Route>
            <Route path="/stocks/new" exact component={StockForm}></Route>
            <Route path="/stocks" component={Stocks}></Route>
            <Route path="/drugs/:id" component={DrugForm}></Route>
            <Route path="/drugs/new" exact component={DrugForm}></Route>
            <Route path="/drugs" component={Drugs}></Route>
            <Route path="/bills/:id" component={BillForm}></Route>
            <Route path="/bills/new" exact component={BillForm}></Route>
            <Route path="/bills" component={Bills}></Route>
            <Route path="/not-found" component={NotFound}></Route>
            <Redirect from="/" exact to="/home"></Redirect>
            <Redirect to="/not-found"></Redirect>
          </Switch>
        </main>
      </React.Fragment>
    );
  }
}

export default App;
