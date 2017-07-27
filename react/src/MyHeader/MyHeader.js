import React, { Component } from 'react';
import { Container, Label, Grid, Divider,Header,Message, Card } from "semantic-ui-react";


class MyHeader extends Component {


    render() {

        const loggedIn = this.props.loggedIn,
            prenom = this.props.user.employeDto.prenom,
            nom = this.props.user.employeDto.nom,
            serviceRh = this.props.user.employeDto.nomRh,
            matricule = this.props.user.employeDto.matricule,
            equipe= this.props.user.employeDto.nomEquipe,
            responsable = this.props.user.employeDto.nomResponsable + " "+this.props.user.employeDto.prenomResponsable,
            nbCa = this.props.user.employeDto.nbCa,
            nbRtt = this.props.user.employeDto.nbRtt,
            nbRc = this.props.user.employeDto.nbRc;

        return (
            <Container as="header">
                <Grid divided='vertically'>
                    <Grid.Row columns={2}>
                        <Grid.Column floated='left' width={4} only="computer">
                            {loggedIn &&
                                <Card as="aside" raised color="blue">
                                    <Card.Content>
                                        <Label color='blue' attached="top center">{prenom} {nom}</Label>
                                        <Card.Meta>Responsable : {responsable}<br/>
                                            Service : {serviceRh}<br/>
                                            Matricule : {matricule}
                                        </Card.Meta>
                                    </Card.Content>
                                </Card>
                            }
                        </Grid.Column>
                        <Grid.Column floated='left' width={16} only="mobile">
                            {loggedIn &&
                                <Message>
                                    <Message.Header>
                                     <Label color='blue' attached="top center">{prenom} {nom}</Label>
                                     </Message.Header>
                                         <div className="mess">
                                             <br/>
                                         Nbr de Congés annuels : {nbCa} jours<br/>
                                          Nbr de R.T.T : {nbRtt} jours<br/>
                                          Nbr de R.C. : {nbRc} heures<br/>
                                         </div>
                                </Message>
                            }
                        </Grid.Column>
                        <Grid.Column floated='left' width={16} only="tablet">
                            {loggedIn &&
                                <Message>
                                    <Message.Header>
                                     <Label color='blue' attached="top center">{prenom} {nom}</Label>
                                     </Message.Header>
                                         <div className="mess">
                                             <br/>
                                         Nbr de Congés annuels : {nbCa} jours<br/>
                                          Nbr de R.T.T : {nbRtt} jours<br/>
                                          Nbr de R.C. : {nbRc} heures<br/>
                                         </div>
                                </Message>
                            }
                        </Grid.Column>
                                       
                    <Header textAlign='center' as="h1">
                        GESTION DES ABSENCES
                        
                    </Header>    
                        <Grid.Column floated='right' width={4} only="computer">
                            {loggedIn &&
                                <Card as="aside" raised color="black">
                                    <Card.Content>
                                        <Label color='blue' attached="top center">Service : {equipe}</Label>
                                        <Card.Meta>
                                            
                                          Nbr de Congés annuels : {nbCa} jours<br/>
                                          Nbr de R.T.T : {nbRtt} jours<br/>
                                          Nbr de R.C. : {nbRc} heures<br/>
                                               
                                        </Card.Meta>
                                        
                                    </Card.Content>
                                </Card>
                            }
                            
                        </Grid.Column>
                    </Grid.Row>
                </Grid>
                <Divider hidden />
            </Container>
        );
    }
}

export default MyHeader;