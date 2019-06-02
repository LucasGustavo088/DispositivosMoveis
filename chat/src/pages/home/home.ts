import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
// import { Storage } from "@ionic/storage";

@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage {

  dados = {
    nomeUsuario: '',
    nomeSala: ''
  }

  constructor(public navCtrl: NavController) {

  }

  entrarSala() {
    this.navCtrl.push('SalaPage', {dadosHome: this.dados});
  }

}
