import React, { Component } from 'react';
import {Container, Message, Label, Header, Divider} from "semantic-ui-react";


class IndexPage extends Component {

    componentWillMount() {
        if (!this.props.loggedIn) {
           this.props.redirect();
        }
    }

    render() {
        const nom = this.props.user.employeDto.nom+" " +this.props.user.employeDto.prenom;
        
        return ( 
                <Container as="main" text>
                    <Divider section />                    
                    <Header textAlign='center'>
                        Bienvenue {nom}
                        
                    </Header>    

                    <Message>
                        <Message.Header>
                        <Label color='red' attached="top center">Message important</Label>
                        </Message.Header>
                            <div className="mess">
                            Vos congés annuels doivent être déposés au moins 15 jours à l'avance.<br/>
                            Dans le cas contraire, ceux-ci pourront être refusé.
                            </div>
                    </Message>
                <Divider hidden />
                                       
                </Container>
            
        );
    }
}

export default IndexPage;