import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Avatar } from 'src/model';
import { AppConfigService } from './app-config.service';

@Injectable({
  providedIn: 'root'
})
export class AvatarHttpService {

  avatars: Array<Avatar> = new Array<Avatar>();
  avatarUrl: string;

  constructor(private http: HttpClient, private appConfig: AppConfigService) {
    this.avatarUrl = this.appConfig.backEndUrl + "avatar/"
    this.load();
  }

  findAll(): Array<Avatar> {
    return this.avatars;
  }

  findById(id: number): Observable<Avatar> {
    return this.http.get<Avatar>(this.avatarUrl + id);
  }

  create(avatar: Avatar) {
    this.http.post<Avatar>(this.avatarUrl, avatar).subscribe(resp => {
      this.load();
    }, error => console.log(error));
  }

  modify(avatar: Avatar) {
    this.http.put<Avatar>(this.avatarUrl + avatar.id, avatar).subscribe(resp => {
      this.load();
    }, error => console.log(error));
  }

  deleteById(id: number) {
    this.http.delete<void>(this.avatarUrl + id).subscribe(resp => {
      this.load();
    }, error => console.log(error));
  }

  load() {
    this.http.get<Array<Avatar>>(this.avatarUrl).subscribe(response => {
      this.avatars = response;
    }, error => console.log(error));
  }
}
