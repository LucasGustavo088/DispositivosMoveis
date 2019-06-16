import { UsuarioService } from './usuario.service';
import { Injectable } from "@angular/core";
import { ThrowStmt } from "@angular/compiler";
import { AngularFireDatabase } from 'angularfire2/database';

@Injectable()
export class MensagemService{
    constructor (private db: AngularFireDatabase){
    }
    
    salas = [
        {id: 1, nomeSala: 'Cinema', mensagens: []},
        {id: 2, nomeSala: 'Curiosidades', mensagens: []},
        {id: 3, nomeSala: 'Esportes', mensagens: []},
    ];

    addMensagem (mensagem, idSala){
        this.db.list("/sala_cadastro/" + idSala).push({            
            mensagem   
        }); 
    }

    getMensagens(idSala) {
        let mensagensDaSala: any;
        mensagensDaSala = this.db.list("/sala_cadastro/" + idSala);

        return mensagensDaSala;
    }

    carregarSalas() {
        console.log(this.salas);
        return this.salas;
    }

    carregarDadosSala(id_sala) {
        let sala = {id: -1};
        
        this.salas.forEach(function(salaAtual) {
            if(id_sala == salaAtual.id) {
                sala = salaAtual;
            }
        });

        return sala;
    }
}