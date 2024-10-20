<template>
  <div class="content">
    <div class="row">
      <div class="col-12">
        <card class="card-plain">
          <!-- <template slot="header">
            <h4 class="card-title">Table on Plain Background</h4>
            <p class="category">Here is a subtitle for this table</p>
          </template>-->
          <div class="table-full-width text-left">
            <wishList-table
            v-if="wishLists && wishLists.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:wishLists="wishLists"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-wish-lists="getAllWishLists"
             >

            </wishList-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/index";

import WishListTable from "@/components/WishListTable";
import WishListService from "../services/WishListService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    WishListTable,
  },
  data() {
    return {
      wishLists: [],
	  totalElements: 0,
      page: 0,
      searchQuery: '',     
      table1: {
        title: "Simple Table",
        columns: [...tableColumns],
        data: [...tableData],
      },
    };
  },
  methods: {
    async getAllWishLists(sortBy='wishListId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await WishListService.getAllWishLists(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.wishLists.length) {
					this.wishLists = response.data.wishLists;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching wishLists:", error);
        }
        
      } catch (error) {
        console.error("Error fetching wishList details:", error);
      }
    },
  },
  mounted() {
    this.getAllWishLists();
  },
  created() {
    this.$root.$on('searchQueryForWishListsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllWishLists();
    })
  }
};
</script>
<style></style>
