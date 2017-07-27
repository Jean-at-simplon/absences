import React, { Component } from 'react';
import { Router, Route } from "react-router";
import MyHeader from "../MyHeader/MyHeader.js";
import MyNav from "../MyNav/MyNav.js";
import APropos from "../APropos/APropos.js";
import LoginPage from "../LoginPage/LoginPage.js";
import ValidRh from "../ValidRh/ValidRh.js";
import IndexPage from "../IndexPage/IndexPage.js";
import EditingPage from "../EditingPage/EditingPage.js";
import ReadingPage from "../ReadingPage/ReadingPage.js";
import ConclusionsEditingPage from "../ConclusionsEditingPage/ConclusionsEditingPage.js";
import EntitiesPage from "../EntitiesPage/EntitiesPage.js";
import PromoManagementPage from "../PromoManagementPage/PromoManagementPage.js";
import createBrowserHistory from "history/createBrowserHistory";




const history = createBrowserHistory();

class MySite extends Component {

    loginPage = () => {
        return <LoginPage name="Page de Login" getUser={this.props.getUser} />
    }

    validRh = () => {
        return <ValidRh 
        loggedIn={this.props.loggedIn} 
        name="Validation Rh" 
        user={this.props.user} 
        deleteUser={this.props.deleteUser} 
        redirect={this.redirectToLogin} />
    }

    indexPage = () => {
        return <IndexPage 
        loggedIn={this.props.loggedIn} 
        user={this.props.user} 
        deleteUser={this.props.deleteUser} 
        redirect={this.redirectToLogin} />
    }

    editingPage = () => {
        return <EditingPage
            deleteUser={this.props.deleteUser}
            user={this.props.user}
            redirect={this.redirectToLogin}
            loggedIn={this.props.loggedIn}
        />
    }

    readingPage = () => {
        return <ReadingPage
            deleteUser={this.props.deleteUser}
            user={this.props.user}
            redirect={this.redirectToLogin}
            loggedIn={this.props.loggedIn}
        />
    }

    conclusionsEditingPage = () => {
        return <ConclusionsEditingPage 
        user={this.props.user} 
        redirect={this.redirectToLogin} 
        deleteUser={this.props.deleteUser}
        loggedIn={this.props.loggedIn} />
    }

    entitiesPage = () => {
        return <EntitiesPage 
        user={this.props.user} 
        redirect={this.redirectToLogin} 
        deleteUser={this.props.deleteUser}
        loggedIn={this.props.loggedIn} />
    }

    promoManagementPage = () => {
        return <PromoManagementPage 
        user={this.props.user} 
        redirect={this.redirectToLogin} 
        deleteUser={this.props.deleteUser}
        loggedIn={this.props.loggedIn} />
    }

    aPropos = () => {
        return <APropos 
        user={this.props.user} 
        redirect={this.redirectToLogin} 
        deleteUser={this.props.deleteUser}
        loggedIn={this.props.loggedIn} />
    }

    myHeader = () => {
        return <MyHeader 
        redirect={this.redirectToLogin} 
        deleteUser={this.props.deleteUser}
        loggedIn={this.props.loggedIn} 
        user={this.props.user} />
    }

    myNav = () => {
        return <MyNav user={this.props.user} deleteUser={this.props.deleteUser} />
    }

    checkRoleForRouting = () => {
        if (this.props.user.role === "4") {
            return "administrateur"
        } else {
            return "accueil"
        }
    }

    redirectToLogin = () => {
        history.push("/welcome");
    }




    componentDidUpdate() {
        this.props.loggedIn ? (
            history.push('/app/' + this.checkRoleForRouting())
            ) : (
                history.push('/welcome')
                );
    }



    componentWillMount() {
        this.redirectToLogin();
    }

    render() {
        return (
            <Router history={history}>
                <div>
                    <Route path="/" render={this.myHeader} />
                    <Route exact path="/welcome" render={this.loginPage} />
                    <Route path="/app/" render={this.myNav} />
                    <Route exact path="/app/validationRh" render={this.validRh} />
                    <Route exact path="/app/création" render={this.entitiesPage} />
                    <Route exact path="/app/consultationRh" render={this.promoManagementPage} />
                    <Route exact path="/app/consultation" render={this.readingPage} />
                    <Route exact path="/app/consultationEquipe" render={this.editingPage} />
                    <Route exact path="/app/création" render={this.diaryCreationPage} />
                    <Route exact path="/app/validationResp" render={this.conclusionsEditingPage} />
                    <Route exact path="/app/édition_questionnaire" render={this.questionEditingPage} />
                    <Route exact path="/app/Apropos" render={this.APropos} />
                    <Route path="/app/accueil" render={this.indexPage} />
                </div>
            </Router>
        );
    }
}

export default MySite;