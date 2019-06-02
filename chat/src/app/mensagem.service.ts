import { Injectable } from "@angular/core";

@Injectable()
export class MensagemService{
    salas = [
        {nomeSala: 'Cinema', mensagens: []},
        {nomeSala: 'Curiosidades', mensagens: []},
        {nomeSala: 'Esportes', mensagens: []},
    ];

    addMensagem (mensagem, sala){
        console.log(mensagem); console.log(sala);
        this.salas.forEach(function(salaAtual, index) {
            if(salaAtual.nomeSala == sala) {
                salaAtual.mensagens.push(mensagem);
            }
        });
    }

    getMensagens(sala) {
        let mensagensDaSala = [];
        this.salas.forEach(function(salaAtual, index) {
            if(salaAtual.nomeSala == sala) {
                mensagensDaSala = salaAtual.mensagens;
            }
        });
        return mensagensDaSala;
    }
}