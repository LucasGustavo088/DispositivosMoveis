import { UsuarioService } from './usuario.service';
import { Injectable } from "@angular/core";
import { ThrowStmt } from "@angular/compiler";

@Injectable()
export class MensagemService{
    salas = [
        {id: 1, nomeSala: 'Cinema', mensagens: []},
        {id: 2, nomeSala: 'Curiosidades', mensagens: []},
        {id: 3, nomeSala: 'Esportes', mensagens: []},
    ];

    addMensagem (mensagem, idSala){
        console.log(mensagem); console.log(idSala);
        this.salas.forEach(function(salaAtual, index) {
            if(salaAtual.id == idSala) {
                salaAtual.mensagens.push(mensagem);
            }
        });
    }

    getMensagens(idSala) {
        let mensagensDaSala = [];
        this.salas.forEach(function(salaAtual, index) {
            if(salaAtual.id == idSala) {
                mensagensDaSala = salaAtual.mensagens;
            }
        });

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