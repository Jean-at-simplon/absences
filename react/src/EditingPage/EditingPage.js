import React, { Component } from 'react';
import { Container, Divider, Table, Header, Grid } from "semantic-ui-react";
import axios from "axios";

class EditingPage extends Component {

constructor(props) {
        super(props);
        this.props=props;
        this.state = {
            absences:[],
           
        }
    }

    componentWillMount() {
        if (!this.props.loggedIn) {
            this.props.redirect();
        } 
    }

    componentDidMount(){
        const equipe = this.props.user.employeDto.nomEquipe;
        axios.get(`/validation/listeAbsences?equipe=`+equipe+'&statut=3')
            .then(res => {
                this.setState({
                absences: res.data
                });
            })
    }

 

    render() {
        const absences = this.state.absences
            
        return ( 
                <Container as="main" text>
                    <Divider section />                    
                    <Header textAlign='center'>
                        Les absences de votre équipe
                        
                    </Header>    
                    
                    <Grid padded doubling centered columns={1}>
                        <Grid.Row>
                            <Grid.Column>
                                <Table celled padded>
                                    <Table.Header>
                                        <Table.Row>
                                            <Table.HeaderCell>N° demande</Table.HeaderCell>
                                            <Table.HeaderCell>Nom</Table.HeaderCell>
                                            <Table.HeaderCell>Prenom</Table.HeaderCell>
                                            <Table.HeaderCell>Type</Table.HeaderCell>
                                            <Table.HeaderCell>Début</Table.HeaderCell>
                                            <Table.HeaderCell>Fin</Table.HeaderCell>                            
                                        </Table.Row>
                                    </Table.Header>
                                    <Table.Body>
                                        {absences.map(
						                (absence, i) => <Table.Row key={i}> 
                                            <Table.Cell>{absence.numDemande}</Table.Cell>
                                            <Table.Cell>{absence.nom}</Table.Cell>
                                            <Table.Cell>{absence.prenom}</Table.Cell>
                                            <Table.Cell>{absence.type}</Table.Cell>
                                            <Table.Cell>{absence.debut.substr(-2) + "/" +  absence.debut.substr(5,2) + "/" + absence.debut.substr(0,4)}</Table.Cell>
                                            <Table.Cell>{absence.fin.substr(-2) + "/" +  absence.fin.substr(5,2) + "/" + absence.fin.substr(0,4)}</Table.Cell>
                                         </Table.Row>)
				                        }
                                    </Table.Body>
                                </Table>       
                            </Grid.Column>
                            
                        </Grid.Row>
                    </Grid>
                                          
                </Container>
            
        );
    }
}

export default EditingPage;