import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { CybersecurityAppSharedModule } from 'app/shared/shared.module';
import { CybersecurityAppCoreModule } from 'app/core/core.module';
import { CybersecurityAppAppRoutingModule } from './app-routing.module';
import { CybersecurityAppHomeModule } from './home/home.module';
import { CybersecurityAppEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    CybersecurityAppSharedModule,
    CybersecurityAppCoreModule,
    CybersecurityAppHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    CybersecurityAppEntityModule,
    CybersecurityAppAppRoutingModule,
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, FooterComponent],
  bootstrap: [MainComponent],
})
export class CybersecurityAppAppModule {}
