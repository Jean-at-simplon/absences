import React, { Component } from 'react';
import { Grid, Container, Input, Button, Header, Divider, Message, Form } from "semantic-ui-react";

import axios from 'axios';

let absencePlaceholder="Choisir le type d'absence";

class EntitiesPage extends Component {

  constructor(props){
      super(props); // Récupère le Props du parent
      this.props=props;
      this.state={ // Définition des propriétés du State
        isHidden:true,
        statut: '',
        type: '',
        debut: '',
        fin: '',
        matricule: '',
        types: [],
        selectedId: 0, 
        message:''
      };
 
  }

  componentDidMount() {
    axios
      .get('/type/listeTypeAbsence')
      .then(res => {
        this.setState({
          types: res.data
        });
      })
      .catch((error) => {
        console.log("Axios : Problème d'accès à la ressource /type/listeTypeAbsence.");
    });

    axios
      .get('/statut/getStatutByCode?code=0')
      .then(res => {
           this.setState({statut: res.data.nom});
         })
       .catch((error) => {
           console.log("Axios : Problème d'accès à la ressource /statut/getStatutByCode?code=0.");
      });
  }

  listeType = (indice, type) => {
    let res;
    if (indice!==3 && indice!==4) {
      res=<option key={indice}>{type}</option>;
    }
    return res;
  }

  handleDebutChange = (event) => {
    this.setState({debut: event.target.value,});
  }

  handleFinChange = (event) => {
    this.setState({fin: event.target.value,});
  }

  handleTypeChange = (event) => {
    this.setState({type: event.target.value,});
  }

  

  creerAbsence = (e) => {
      e.preventDefault();
    axios.post('/absence/creerAbsence/',
          {
            debut: this.state.debut,
            fin: this.state.fin,
            type: this.state.type,
            statut: this.state.statut,
            matricule: this.props.user.employeDto.matricule
          })
      .then(res => {
        console.log("Resultat création absence : ");
        console.log(res.data);
        this.setState({
                    message: 'Votre demande est prise en compte',
                    isHidden: false
                })
      })
      .catch((error) => {
          console.log("Axios : Problème d'accès à la ressource /absence/creerAbsence/.");
      ;
          this.setState({
                    message: 'Informations saisies erronées. Veuillez vérifier l\'exactitude de vos informations.',
                    isHidden: false
                })
      });
  }

  annuler = () => {
    if (this.state.type===absencePlaceholder && this.state.debut==="" && this.state.fin==="") {
      return;
    };

    this.setState({
      type: absencePlaceholder,
      debut: "",
      fin: ""
    });
  }

  render() {
      const isHidden = this.state.isHidden,
            debut = this.state.debut,
            fin = this.state.fin,
            types=this.state.types;
    return (
        <Container as="main" textAlign="center">
                    <Divider section />
                    <Header textAlign='center' as="h1">
                        Demande de congé
                    </Header>
                    <Grid padded doubling centered columns={2}>
                        <Grid.Row>
                            <Grid.Column>
                                <Form widths="equal">
                                    <Form.Field>
                                        <label>Type d'absence</label>
                                        <select ref="userInput" defaultValue="" required onChange={this.handleTypeChange}>
                                            <option value="" disabled></option>
                                            {
                                              types.map(function(type,i) {
                                                return <option key={i}
                                                  value={type.nom}>{type.nom}</option>;
                                              })
                                            }
                                          </select>
                                        
        			            	</Form.Field>
                                    <Form.Field>
                                        <label>Date de début</label>
                                        <Input
                                            size="tiny"
                                            type="date"
                                            placeholder="Date de debut"
                                            id="debut"
                                            value={debut}
                                            onChange={this.handleDebutChange}
                                        />
                                    </Form.Field>
                                    <Form.Field>
                                        <label>Date de fin</label>
                                        <Input
                                            size="tiny"
                                            type="date"
                                            placeholder="Date de fin"
                                            id="fin"
                                            value={fin}
                                            onChange={this.handleFinChange}
                                        />
                                    </Form.Field>
                                    <Button onClick={this.creerAbsence}>Valider</Button>
                                    <Button onClick={this.annuler}>Annuler</Button>
                                </Form>
                            </Grid.Column>
                        </Grid.Row>
                    </Grid>

                    <Message hidden={isHidden} error={!isHidden}>
                        <Message.Header>
                            {this.state.message}
                            </Message.Header>
                        <p>
                            </p>
                    </Message>
                </Container>
        
    );
  }
}

export default EntitiesPage;