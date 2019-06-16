import { LoginPage } from './../pages/login/login';
import { MensagemService } from './mensagem.service';
import { UsuarioService } from './usuario.service';
import { BrowserModule } from '@angular/platform-browser';
import { ErrorHandler, NgModule } from '@angular/core';
import { IonicApp, IonicErrorHandler, IonicModule } from 'ionic-angular';
import { SplashScreen } from '@ionic-native/splash-screen';
import { StatusBar } from '@ionic-native/status-bar';

import { AngularFireModule } from 'angularfire2';
import { AngularFireDatabaseModule } from 'angularfire2/database';

import { MyApp } from './app.component';
import { HomePage } from '../pages/home/home';

export const firebaseConfig = {
  apiKey: "AIzaSyCQPpNc7xUmf8m2aUgROSaiuDNCYEU4MRk",
  authDomain: "chat-3e279.firebaseapp.com",
  databaseURL: "https://chat-3e279.firebaseio.com",
  projectId: "chat-3e279",
  storageBucket: "chat-3e279.appspot.com",
  messagingSenderId: "1045267378067",
  appId: "1:1045267378067:web:a43a02539820a0ce"
};

@NgModule({
  declarations: [
    MyApp,
    HomePage,
    LoginPage
  ],
  imports: [
    BrowserModule,
    IonicModule.forRoot(MyApp),
    AngularFireModule.initializeApp(firebaseConfig),
    AngularFireDatabaseModule
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,
    HomePage,
    LoginPage
  ],
  providers: [
    StatusBar,
    SplashScreen,
    MensagemService,
    UsuarioService,
    { provide: ErrorHandler, useClass: IonicErrorHandler }
  ]
})
export class AppModule { }
