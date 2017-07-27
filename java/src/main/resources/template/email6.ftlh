<div>
    <!--HEADER-->

    <#include "/include/entete.ftl">

        <!--START OF BODY-->

        <div style="padding: 15px 15px 30px 15px; font-family: Verdana, Arial, Helvetica, sans-serif; font-size:1em; background-color: #fff; color:#333;">
            <p>Bonjour</p>
            <p>
            	Votre demande n° <span>${numDemande}</span> pour l'obtention de 
            	<span>${typeConge}</span> du
                <span>${debutConge}</span> au
                <span>${finConge}</span> n'a pas été approuvée par le service Rh pour le motif ci dessous.  
            </p>
            <p>
            	<span>${commentaire}</span>
            </p>
            <p>
                En conséquence, votre demande est annulée.
            </p>
            <p>
                Cordialement,
            </p>
            <p>
                &emsp; <em>Le service Rh</em>
            </p>
            <p style="color:red; font-size: 0.6em">
                *Ce message vous a été envoyé automatiquement, merci de ne pas répondre.
            </p>
        </div>

        <!--FOOTER-->

        <#include "/include/footer.ftl">
</div>