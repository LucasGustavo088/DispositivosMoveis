import { UsuarioService } from './../../app/usuario.service';
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
  sala = {};
  mensagens = [];
  mensagem;
  idSala = -1;

  constructor(public navCtrl: NavController, public navParams: NavParams, public mensagemService : MensagemService, public usuarioService: UsuarioService) {
    this.idSala = this.navParams.get("id_sala");
    this.sala = this.mensagemService.carregarDadosSala(this.idSala);
    this.mensagens = this.mensagemService.getMensagens(this.idSala);
  }

  enviarMensagem() {
    let usuarioLogado = this.usuarioService.carregarUsuarioSessao();
    usuarioLogado.IconeCadastro = this.usuarioService.carregarIcone(usuarioLogado.icone);
    let novaMensagem = {
      mensagem: this.mensagem,
      UsuarioCadastro: usuarioLogado
    };

    this.mensagemService.addMensagem(novaMensagem, this.idSala);
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad SalaPage');
  }

}
