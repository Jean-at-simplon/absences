import React, { Component } from 'react';
import { Container } from "semantic-ui-react";
import axios from "axios";
import MyMenu from "./MyMenu/MyMenu.js";

class MyNav extends Component {

    constructor(props) {
        super(props);
        this.state = {
            activeItem: "",
            links: []
        }
    }

    componentWillMount() {
        console.log("will mount : " + this.props.user.role)
        this.setState({
            links: this.linksByRole(null)
        }, () => {
            console.log(this.state.links)
        })

    }

    linksByRole = (arrayOfLinks) => {
        const roleName = this.props.user.role;
        let result = []
        if (arrayOfLinks !== null) {
            let result = arrayOfLinks
        }

        if (roleName === "3") {
            result.push(
                {
                    url: "/app/validationRh",
                    name: "Demandes à valider"
                }, {
                    url: "/app/consultationRh",
                    name: "Tableau des absences"
                },
               {
                    url: "/app/Apropos",
                    name: "A propos"
                }
            )

        } else if (roleName === "2") {
            result.push(
                                {
                    url: "/app/création",
                    name: "Demande d'absence"
                }, {
                    url: "/app/consultation",
                    name: "Consultation des absences"
                }, {
                    url: "/app/consultationEquipe",
                    name: "Absences de l'équipe"
                }, 
                {
                    url: "/app/validationResp",
                    name: "Demandes à valider"
                }, 
                {
                    url: "/app/Apropos",
                    name: "A propos"
                }
            )
        } else if (roleName === "1"){
            console.log("role formateur")
            result.push(
                {
                    url: "/app/création",
                    name: "Demande d'absence"
                }, {
                    url: "/app/consultation",
                    name: "Consultation des absences"
                }, {
                    url: "/app/consultationEquipe",
                    name: "Absences de l'équipe"
                }, {
                    url: "/app/Apropos",
                    name: "A propos"
                }
            )
        }
        return result;
    }

    componentWillUpdate() {
        console.log("will update mynav")
        console.log(this.linksByRole(null))
        console.log(this.state.links)
        if (this.linksByRole(null).length !== this.state.links.length && this.linksByRole[0] !== this.state.links[0]) {
            this.setState({
                links: this.linksByRole(this.state.links)
            })
        }
    }

    loggingOut = (e, { name }) => {
        const self = this;
        this.handleItemClick(e, { name });
        axios.get("/logout")
            .then(function (response) {
                self.props.deleteUser();
            })
    }

    handleItemClick = (e, { name }) => {
        this.setState({ activeItem: name })
    }

    render() {
        const activeItem = this.state.activeItem;
        const links = this.state.links;

        return (
            <Container as="nav">
                <MyMenu items={links} deleteUser={this.props.deleteUser} />
            </Container>
        );
    }
}

export default MyNav;