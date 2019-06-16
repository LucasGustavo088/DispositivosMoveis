import { MensagemService } from './../../app/mensagem.service';
import { Component } from '@angular/core';
import { IonicPage, NavController } from 'ionic-angular';
import { UsuarioService } from '../../app/usuario.service';

/**
 * Generated class for the IndexPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-index',
  templateUrl: 'index.html',
})
export class IndexPage {

  usuario_logado = {};
  salas = [];
  id_sala = -1;

  constructor(public navCtrl: NavController, public usuarioService: UsuarioService, public mensagemService: MensagemService) {
    this.usuario_logado = this.usuarioService.carregarUsuarioSessao();
    this.salas = this.mensagemService.carregarSalas();  
    console.log(this.salas);
  }

  entrarSala() {
    this.navCtrl.push('SalaPage', {id_sala: this.id_sala});
  }

}
