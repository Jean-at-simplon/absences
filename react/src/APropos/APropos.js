import React, { Component } from 'react';
import {Message, Label} from "semantic-ui-react";


export default class APropos extends Component {

     componentWillMount() {
        if (!this.props.loggedIn) {
           this.props.redirect();
        }
    }

    render() {
        return (
            <Message>
                        
                            <div className="mess">
                           GestionAbsence V1 - &copy 2017
                            </div>
                    </Message>
        );
    }
}
