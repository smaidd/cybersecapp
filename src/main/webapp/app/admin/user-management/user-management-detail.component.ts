import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { User } from 'app/core/user/user.model';

@Component({
  selector: 'jhi-user-mgmt-detail',
  templateUrl: './user-management-detail.component.html',
})
export class UserManagementDetailComponent implements OnInit {
  user: User | null = null;
  passwordHidden = true;
  counterPassword = 5;

  constructor(private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.counterPassword = 5;
    this.route.data.subscribe(({ user }) => (this.user = user));
  }

  counterPasswordToShow(): void {
    this.counterPassword--;
    if (this.counterPassword === 0) {
      this.passwordHidden = false;
    }
  }
}
