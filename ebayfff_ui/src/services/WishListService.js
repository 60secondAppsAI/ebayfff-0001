import http from "../http-common";

class WishListService {
  getAllWishLists(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/wishList/wishLists`, searchDTO);
  }

  get(wishListId) {
    return this.getRequest(`/wishList/${wishListId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/wishList?field=${matchData}`, null);
  }

  addWishList(data) {
    return http.post("/wishList/addWishList", data);
  }

  update(data) {
  	return http.post("/wishList/updateWishList", data);
  }
  
  uploadImage(data,wishListId) {
  	return http.postForm("/wishList/uploadImage/"+wishListId, data);
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

export default new WishListService();
