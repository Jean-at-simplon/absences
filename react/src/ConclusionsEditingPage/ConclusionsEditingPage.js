import React, { Component } from 'react';
import axios from "axios";
import { Container, Divider, Header, Table, Form, Input, Grid, Button } from "semantic-ui-react";


   
class ConclusionsEditingPage extends Component {

    constructor(props) {
        super(props);
        this.props=props;
        this.state = {
            absences:[],
            numDemande:'',
            commentaire:'',
            validation:''
        }
    }

    componentWillMount() {
        if (!this.props.loggedIn) {
            this.props.redirect();
        } 
    }

    componentDidMount(){
        const equipe = this.props.user.employeDto.nomEquipe;
        axios.get(`/validation/listeAbsences?equipe=`+equipe+'&statut=1')
            .then(res => {
                this.setState({
                absences: res.data
                });
            })
    }

    handleNumDemandeChange = (event) => {
       this.setState({numDemande: event.target.value,});
     }

     handleCommentaireChange = (event) => {
       this.setState({commentaire: event.target.value,});
     }

     handleValidationChange = (event) => {
         this.setState({validation: event.target.value,});
     }

    validerAbsence = (e) => {
      e.preventDefault();
    axios.put('/validation/validationAbsence/',
          {
            numDemande : this.state.numDemande,
            commentaire : this.state.commentaire,
            validation : "2"
          })
      .then(res => {
        console.log("Resultat création absence : ");
        console.log(res.data);
      })
      .catch((error) => {
          console.log("Axios : Problème d'accès à la ressource /absence/creerAbsence/.");
          console.log(this.state.debut,this.state.fin,this.state.type,this.state.statut,this.props.employe.matricule);
      });
  }

    refuserAbsence = (e) => {
      e.preventDefault();
    axios.put('/validation/validationAbsence/',
          {
            numDemande : this.state.numDemande,
            commentaire : this.state.commentaire,
            validation : "4"
          })
      .then(res => {
        console.log("Resultat création absence : ");
        console.log(res.data);
      })
      .catch((error) => {
          console.log("Axios : Problème d'accès à la ressource /absence/creerAbsence/.");
          console.log(this.state.debut,this.state.fin,this.state.type,this.state.statut,this.props.employe.matricule);
      });
  }

  annuler = () => {
   
    this.setState({
      numDemande:'',
      commentaire:'',
      validation:''
    });
  }

     

    render() {
        const absences = this.state.absences,
            numDemande = this.state.numDemande,
            commentaire = this.state.commentaire;
        return ( 
                <Container as="main" text>
                    <Divider section />                    
                    <Header textAlign='center'>
                        Vos demandes à valider
                        
                    </Header>    
                    
                    <Grid padded doubling centered columns={1}>
                        <Grid.Row>
                            <Grid.Column>
                                <Form widths="equal">
                                    <Form.Field>
                                        <label>Numéro de demande</label>
                                        <Input
                                            size="tiny"
                                            type="text"                                      
                                            placeholder="Numéro de demande"
                                            id="numDemande"
                                            value={numDemande}
                                            onChange={this.handleNumDemandeChange}
                                        />
                                    </Form.Field>
                                    <Form.Field>
                                        <label>Commentaire</label>
                                        <Input
                                            size="tiny"
                                            type="textarea"                                            
                                            placeholder="Votre commentaire"
                                            id="commentaire"
                                            value={commentaire}
                                            onChange={this.handleCommentaireChange}
                                        />
                                    </Form.Field>
                                    
                                    <Button onClick={this.validerAbsence}>Valider</Button>
                                    <Button onClick={this.refuserAbsence}>Refuser</Button>
                                    <Button onClick={this.annuler}>Effacer</Button>
                                </Form>
                            </Grid.Column>
                            </Grid.Row>
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

export default ConclusionsEditingPage;