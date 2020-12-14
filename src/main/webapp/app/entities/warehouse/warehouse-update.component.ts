import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IWarehouse, Warehouse } from 'app/shared/model/warehouse.model';
import { WarehouseService } from './warehouse.service';
import { ILocation } from 'app/shared/model/location.model';
import { LocationService } from 'app/entities/location/location.service';

@Component({
  selector: 'jhi-warehouse-update',
  templateUrl: './warehouse-update.component.html',
})
export class WarehouseUpdateComponent implements OnInit {
  isSaving = false;
  locations: ILocation[] = [];

  editForm = this.fb.group({
    id: [],
    warehouseName: [],
    postalCode: [],
    phoneNumber: [],
    locationId: [],
  });

  constructor(
    protected warehouseService: WarehouseService,
    protected locationService: LocationService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ warehouse }) => {
      this.updateForm(warehouse);

      this.locationService.query().subscribe((res: HttpResponse<ILocation[]>) => (this.locations = res.body || []));
    });
  }

  updateForm(warehouse: IWarehouse): void {
    this.editForm.patchValue({
      id: warehouse.id,
      warehouseName: warehouse.warehouseName,
      postalCode: warehouse.postalCode,
      phoneNumber: warehouse.phoneNumber,
      locationId: warehouse.locationId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const warehouse = this.createFromForm();
    if (warehouse.id !== undefined) {
      this.subscribeToSaveResponse(this.warehouseService.update(warehouse));
    } else {
      this.subscribeToSaveResponse(this.warehouseService.create(warehouse));
    }
  }

  private createFromForm(): IWarehouse {
    return {
      ...new Warehouse(),
      id: this.editForm.get(['id'])!.value,
      warehouseName: this.editForm.get(['warehouseName'])!.value,
      postalCode: this.editForm.get(['postalCode'])!.value,
      phoneNumber: this.editForm.get(['phoneNumber'])!.value,
      locationId: this.editForm.get(['locationId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IWarehouse>>): void {
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

  trackById(index: number, item: ILocation): any {
    return item.id;
  }
}
