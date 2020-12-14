import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { CybersecurityAppTestModule } from '../../../test.module';
import { ProductOrderedComponent } from 'app/entities/product-ordered/product-ordered.component';
import { ProductOrderedService } from 'app/entities/product-ordered/product-ordered.service';
import { ProductOrdered } from 'app/shared/model/product-ordered.model';

describe('Component Tests', () => {
  describe('ProductOrdered Management Component', () => {
    let comp: ProductOrderedComponent;
    let fixture: ComponentFixture<ProductOrderedComponent>;
    let service: ProductOrderedService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CybersecurityAppTestModule],
        declarations: [ProductOrderedComponent],
      })
        .overrideTemplate(ProductOrderedComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProductOrderedComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProductOrderedService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ProductOrdered(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.productOrdereds && comp.productOrdereds[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
