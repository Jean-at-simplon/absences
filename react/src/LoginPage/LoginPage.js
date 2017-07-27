import React, { Component } from 'react';
import { Grid, Container, Input, Button, Header, Divider, Message, Form } from "semantic-ui-react";
import axios from "axios";

class LoginPage extends Component {

    constructor(props) {
        super(props);
        this.state = {
            isHidden: true,
            email: "",
            password: ""
        }
    }

    handleEmailChange = (event) => {
        this.setState({
            email: event.target.value
        })
    }

    handlePasswordChange = (event) => {
        this.setState({
            password: event.target.value
        })
    }

    login = (e) => {
        e.preventDefault();
        const self = this;
        let email = this.state.email;
        let password = this.state.password;
        axios.post(`/login?email=${email}&password=${password}`)
            .then(function (response) {
                axios.get("user/authUser")
                    .then(function (response) {
                        if (response.data != null) {
                            self.props.getUser(true, response.data);
                        }
                    })
                    .catch(function (error) {
                        self.setState({
                            isHidden: false
                        })
                        console.log("erreur recup data")
                    })
            })
            .catch(function (error) {
                self.setState({
                    isHidden: false
                })
                console.log("erreur login")
            })
    }

    render() {
        const isHidden = this.state.isHidden,
            email = this.state.email,
            password = this.state.password;

        return (
            
                <Container as="main" textAlign="center">
                    <Divider section />
                    <Header textAlign='center' as="h1">
                        Bienvenue sur votre espace de gestion des absesnces
                    </Header>
                    <Grid padded doubling centered columns={2}>
                        <Grid.Row>

                            <Grid.Column>
                                <Form widths="equal">
                                    <Form.Field>
                                        <Input
                                            size="tiny"
                                            type="email"
                                            placeholder="Votre e-mail"
                                            id="email"
                                            value={email}
                                            onChange={this.handleEmailChange}
                                        />
                                    </Form.Field>
                                    <Form.Field>
                                        <Input
                                            size="tiny"
                                            type="password"
                                            placeholder="Votre mot de passe"
                                            id="password"
                                            value={password}
                                            onChange={this.handlePasswordChange}
                                        />
                                    </Form.Field>
                                    <Button positon="right" onClick={this.login}>Valider</Button>
                                </Form>
                            </Grid.Column>
                        </Grid.Row>
                    </Grid>

                    <Message hidden={isHidden} error={!isHidden}>
                        <Message.Header>
                            Problème de connexion
                            </Message.Header>
                        <p>
                            Informations de connexion erronées. Veuillez vérifier l'exactitude de vos identifiants.
                            </p>
                    </Message>
                </Container>
            
        );
    }
}

export default LoginPage;