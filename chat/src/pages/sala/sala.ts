import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import {MensagemService} from '../../app/mensagem.service';

/**
 * Generated class for the SalaPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-sala',
  templateUrl: 'sala.html',
})
export class SalaPage {

  dados;

  mensagens = [];
  mensagem;

  constructor(public navCtrl: NavController, public navParams: NavParams, public mensagemService : MensagemService) {
    this.dados = this.navParams.get("dadosHome");
    console.log(this.dados);
    this.mensagens = this.mensagemService.getMensagens(this.dados.nomeSala);
  }

  enviarMensagem() {
    let novaMensagem = {
      nomeUsuario: this.dados.nomeUsuario,
      mensagem: this.mensagem 
    };

    this.mensagemService.addMensagem(novaMensagem, this.dados.nomeSala);
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad SalaPage');
  }

}
