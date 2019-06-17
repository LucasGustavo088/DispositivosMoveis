import { UsuarioService } from './../../app/usuario.service';
import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams, AlertController } from 'ionic-angular';
import { enterView } from '@angular/core/src/render3/instructions';
import {AngularFireDatabase} from 'angularfire2/database';
/**
 * Generated class for the LoginPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-login',
  templateUrl: 'login.html',
})
export class LoginPage {

  usuario = {
    nomeUsuario: '',
    icone: ''
  }

  icones = [];

  constructor(public navCtrl: NavController, 
    public navParams: NavParams, 
    public usuarioService: UsuarioService, 
    private alertCtrl: AlertController,
    private db: AngularFireDatabase) {
    this.icones = usuarioService.carregarIcones();
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad LoginPage');
  }

  adicionarUsuario (usuario){        
    //this.notes.push(note);        
    this.db.list("/usuario_cadastro/").push(usuario);    
  }


  logar() {
    if(this.usuarioService.verificarLoginJaExiste(this.usuario)) {
      let alert = this.alertCtrl.create({
        title: 'Esse usuário já existe! Insira outro',
        buttons: [
          {
            text: 'Ok',
            role: 'cancel',
            handler: () => {
            }
          }
        ]
      });
      alert.present();
    } else {
      this.usuarioService.cadastrarELogar(this.usuario);
      this.navCtrl.push('IndexPage');
    }
  }

}
