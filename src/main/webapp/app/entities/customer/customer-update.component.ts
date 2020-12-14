import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';

import { Customer, ICustomer } from 'app/shared/model/customer.model';
import { CustomerService } from './customer.service';

@Component({
  selector: 'jhi-customer-update',
  templateUrl: './customer-update.component.html',
})
export class CustomerUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: [],
    address: [],
    phoneNumber: [],
    email: [],
  });

  constructor(
    protected customerService: CustomerService,
    protected router: Router,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
    this.activatedRoute.data.subscribe(({ customer }) => {
      this.updateForm(customer);
    });
  }

  updateForm(customer: ICustomer): void {
    this.editForm.patchValue({
      id: customer.id,
      name: customer.name,
      address: customer.address,
      phoneNumber: customer.phoneNumber,
      email: customer.email,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const customer = this.createFromForm();
    if (customer.phoneNumber === null) {
      this.router.navigate(['/admin/user-management']);
    } else {
      if (customer.id !== undefined) {
        this.subscribeToSaveResponse(this.customerService.update(customer));
      } else {
        this.subscribeToSaveResponse(this.customerService.create(customer));
      }
    }
  }

  private createFromForm(): ICustomer {
    return {
      ...new Customer(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      address: this.editForm.get(['address'])!.value,
      phoneNumber: this.editForm.get(['phoneNumber'])!.value,
      email: this.editForm.get(['email'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICustomer>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
