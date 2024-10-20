import http from "../http-common";

class WishListItemService {
  getAllWishListItems(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/wishListItem/wishListItems`, searchDTO);
  }

  get(wishListItemId) {
    return this.getRequest(`/wishListItem/${wishListItemId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/wishListItem?field=${matchData}`, null);
  }

  addWishListItem(data) {
    return http.post("/wishListItem/addWishListItem", data);
  }

  update(data) {
  	return http.post("/wishListItem/updateWishListItem", data);
  }
  
  uploadImage(data,wishListItemId) {
  	return http.postForm("/wishListItem/uploadImage/"+wishListItemId, data);
  }




	postRequest(url, data) {
		return http.post(url, data);
      };

	getRequest(url, params) {
        return http.get(url, {
        	params: params
        });
    };

}

export default new WishListItemService();
