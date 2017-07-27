import React, { Component } from 'react';
import { Container, Header, Table, Divider} from "semantic-ui-react";
import axios from "axios";

class ReadingPage extends Component {

    constructor(props) {
        super(props);
        this.state = {
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
            absenceDto:[]
            }

        }
    }


    componentWillMount() {
        if (!this.props.loggedIn) {
            this.props.redirect();
        } 
    }

    componentDidMount() {
        axios.get("../user/getUser?email="+this.props.user.email)
                    .then(res => {
                        if (res.data != null) {
                            this.setState({
                user: res.data
            })
                        }
                    })
                    .catch(function (error) {
                
                        console.log("erreur recup data")
                    })

    }

    render() {
        const absences = this.state.user.absenceDto;
        return ( 
                <Container as="main" text>
                    <Divider section />                    
                    <Header textAlign='center'>
                        Vos précédentes demandes
                        
                    </Header>    
                    <Table>
                                    <Table.Header>
                                        <Table.Row>
                                            <Table.HeaderCell>N° demande</Table.HeaderCell>
                                            <Table.HeaderCell>Type</Table.HeaderCell>
                                            <Table.HeaderCell>Début</Table.HeaderCell>
                                            <Table.HeaderCell>Fin</Table.HeaderCell>       
                                            <Table.HeaderCell>Statut</Table.HeaderCell>
                                            <Table.HeaderCell>Commentaire</Table.HeaderCell>                     
                                        </Table.Row>
                                    </Table.Header>
                                    <Table.Body>
                                        {absences.map(
						                (absence, i) => <Table.Row key={i}> 
                                            <Table.Cell>{absence.numDemande}</Table.Cell>
                                            <Table.Cell>{absence.type}</Table.Cell>
                                            <Table.Cell>{absence.debut.substr(-2) + "/" +  absence.debut.substr(5,2) + "/" + absence.debut.substr(0,4)}</Table.Cell>
                                            <Table.Cell>{absence.fin.substr(-2) + "/" +  absence.fin.substr(5,2) + "/" + absence.fin.substr(0,4)}</Table.Cell>
                                            <Table.Cell>{absence.statut}</Table.Cell>
                                            <Table.Cell>{absence.commentaire}</Table.Cell>
                                         </Table.Row>)
				                        }
                                    </Table.Body>
                                </Table>      
                </Container>
            
        );
    }
}

export default ReadingPage;