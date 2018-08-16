import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpEvent, HttpClientModule } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ResponseType } from '@angular/http';
 
@Injectable()
export class UploadFileService {
 
  constructor(private http: HttpClient) {}
 
  pushFileToStorage(file: File, username : string): Observable<HttpEvent<{}>> {
    let formdata: FormData = new FormData();
 
    formdata.append('file', file);
 
    const req = new HttpRequest('POST', '/api/post/' + username, formdata, {
      reportProgress: true,
      responseType: 'text',
    });
 
    return this.http.request(req);
  }
 
  getFiles(): Observable<string[]> {
    return this.http.get<string[]>('/api/getallfiles');
  }

  getFile(username) : Observable<any> {
    return this.http.get('/api/files/' + username, { responseType : 'blob' });
  }
}