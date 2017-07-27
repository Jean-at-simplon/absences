import React, { Component } from 'react';
import './App.css';
import MySite from "./MySite/MySite.js";


class App extends Component {

  constructor(props) {
    super(props);
    this.state = {
      loggedIn: false,
      user: {
        email:"",
        role:"",
        employeDto :{
          nom:"",
          prenom:"",
          matricule:"",
          email:"",
          nbCa:0,
          nbRtt:0,
          nbRc:0,
          nomRh:"",
          emailRh:"",
          nomEquipe:"",
          nomResponsable:"",
          prenomResponsable:"",
          emailResponsable:""
        },
        absences:[]
      }
    }
  }

  componentDidMount() {
    console.log("App did mount")
    this.checkSessionStorage();
  }

  componentDidUpdate(prevProps, prevState) {
    console.log("App did update")
    sessionStorage.setItem('state', JSON.stringify(this.state));
  }

  checkSessionStorage = () => {
    try {
      if (sessionStorage.getItem('state') !== null) {
        this.setState((prevState) => {
          return prevState = JSON.parse(sessionStorage.getItem('state'))
        });
      }
    } catch (err) {
      console.log(err);
    }
  }

  getUser = (boolean, newUser) => {
    this.setState({
      loggedIn: boolean,
      user: newUser
    })
  }

  deleteUser = () => {
    this.setState({
      loggedIn: false,
      user: {
        email:"",
        role:"",
        employeDto :{
          nom:"",
          prenom:"",
          matricule:"",
          email:"",
          nbCa:"",
          nbRtt:"",
          nbRc:"",
          nomRh:"",
          emailRh:"",
          nomEquipe:"",
          nomResponsable:"",
          prenomResponsable:"",
          emailResponsable:""
        },
        absences:[]
      }
    })
  }

  getEquipe = () => {
    if (this.state.user.nomEquipe !== undefined) {
      return this.state.user.nomEquipe
    } else {
      return this.state.user.nomEquipe
    }
  }

  
  render() {
    console.log("App render")
    return (
      <MySite
        user={this.state.user}
        loggedIn={this.state.loggedIn}
        getUser={this.getUser}
        deleteUser={this.deleteUser}
        Equipe={this.getEquipe}
      />
    );
  }
}

export default App;
